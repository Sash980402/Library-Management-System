package org.example.library_management_system.repo.util;

import org.example.library_management_system.repo.CrudRepository;
import org.example.library_management_system.repo.custom.*;
import org.example.library_management_system.repo.custom.impl.*;

//Factory Design Patton
//singleton
public class RepoFactory {

    private final static RepoFactory instance = new RepoFactory();
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final CategoryRepo categoryRepo;
    private final MemberRepo memberRepo;
    private final PublisherRepo publisherRepo;

    private RepoFactory() {
        authorRepo = new AuthorRepoIMPL();
        bookRepo = new BookRepoIMPL();
        categoryRepo = new CategoryRepoIMPL();
        memberRepo = new MemberRepoIMPL();
        publisherRepo = new PublisherRepoIMPL();
    }

    public <T extends CrudRepository>T getRepo(RepoTypes type) {
        switch (type) {
            case BOOK_REPO : return (T) this.bookRepo;
            case AUTHOR_REPO : return (T) this.authorRepo;
            case CATEGORY_REPO : return (T) this.categoryRepo;
            case MEMBER_REPO : return (T) this.memberRepo;
            case PUBLISHER_REPO : return (T) this.publisherRepo;
            default : return null;
        }
    }

    public static RepoFactory getInstance() {
        return instance;
    }
}
