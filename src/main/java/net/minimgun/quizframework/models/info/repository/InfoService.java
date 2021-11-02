package net.minimgun.quizframework.models.info.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.minimgun.quizframework.exceptions.EntityNotFoundException;
import net.minimgun.quizframework.exceptions.NoEntityInDatabaseListException;
import net.minimgun.quizframework.models.CurrentEntityHelper;
import net.minimgun.quizframework.models.info.entity.Info;

@Service
public class InfoService {

    @Autowired
    private InfoRepository repository;

    /**
     * Returns the current {@link Info} entity.
     * 
     * @return The current {@link Info}.
     * @see CurrentEntityHelper#getCurrent(net.minimgun.quizframework.models.CurrentEntityInferface)
     */
    public Info getInfo() {
        Info info;
        try {
            info = CurrentEntityHelper.getCurrent(repository);
        } catch (NoEntityInDatabaseListException e) {
            info = new Info("Quiz-Framework by MINIMgun");
        }
        return info;
    }

    public Info getInfo(long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Info.class, id));
    }

    public void addInfo(Info info) {
        repository.save(info);
    }
}
