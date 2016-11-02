/*
 * Copyright (c) 2016, Gopal S Akshintala. 
 * This source code is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
 * http://creativecommons.org/licenses/by-sa/4.0/
 */

package resource;

/**
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
    public int hashCode() {
        return this.mResourceName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Resource) && this.mResourceName.equals(((Resource) obj).mResourceName);
    }

    // These methods hold business logic for various resources like dbs
    public abstract void readFromResource();

    public abstract void writeToResource();

    public abstract void deleteFromResource();
}
