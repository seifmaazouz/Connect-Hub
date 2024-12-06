package connecthub.backend.database;
import connecthub.backend.loaders.ContentLoader;
import connecthub.backend.models.Content;
import connecthub.backend.models.Expirable;

public abstract class ExpirableContentDatabase<T extends Content & Expirable> extends ContentDatabase<T> {

    public ExpirableContentDatabase(ContentLoader<T> contentLoader) {
        super(contentLoader);
    }
    
    public abstract void deleteExpiredContent();
}
