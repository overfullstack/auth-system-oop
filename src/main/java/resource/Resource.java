package resource; /**
 * Created by gakshintala on 4/15/16.
 */

/**
 * This Class is extended by various resources to implement their business logic
 * Our system depends only on the abstract ref to support Dependency Injection.
 */
public abstract class Resource {
    private String mResourceName;

    public Resource(String resourceName) {
        this.mResourceName = resourceName;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Resource) && this.mResourceName.equals(((Resource) obj).mResourceName);
    }

    @Override
    public int hashCode() {
        return this.mResourceName.hashCode();
    }

    // These methods hold business logic for various resources like dbs
    public abstract void readFromResource();

    public abstract void writeToResource();

    public abstract void deleteFromResource();
}
