import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { EditPassword } from 'src/app/api/models';
import { EditControllerService } from 'src/app/api/services';

@Component({
  templateUrl: './edit-authorization.component.html',
  styleUrls: ['./edit-authorization.component.css'],
})
export class EditAuthorizationComponent implements OnInit {
  id?: string;
  editPassword?: EditPassword = {};
  invalid: boolean = false;
  constructor(
    private api: EditControllerService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
  }

  authorize(): void {
    this.api
      .editQuiz$Response({
        id: this.id,
        body: this.editPassword,
      })
      .subscribe(
        () => {
          let tokenIds = JSON.parse(
            sessionStorage.getItem('edit_tokens')
          ) as String[];
          if (!tokenIds) {
            tokenIds = new Array();
          }
          tokenIds.push(this.id);
          sessionStorage.setItem('edit_tokens', JSON.stringify(tokenIds));
          this.router.navigate(['edit', this.id]);
        },
        () => {
          this.invalid = true;
        }
      );
  }
}
