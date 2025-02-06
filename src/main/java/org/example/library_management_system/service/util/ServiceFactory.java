package org.example.library_management_system.service.util;

import org.example.library_management_system.repo.util.RepoFactory;
import org.example.library_management_system.repo.util.RepoTypes;
import org.example.library_management_system.service.SuperService;
import org.example.library_management_system.service.custom.AuthorService;
import org.example.library_management_system.service.custom.BookService;
import org.example.library_management_system.service.custom.MemberService;
import org.example.library_management_system.service.custom.PublisherService;
import org.example.library_management_system.service.custom.impl.AuthorServiceIMPL;
import org.example.library_management_system.service.custom.impl.BookServiceIMPL;
import org.example.library_management_system.service.custom.impl.MemberServiceIMPL;
import org.example.library_management_system.service.custom.impl.PublisherServiceIMPL;

public class ServiceFactory {
    private static final RepoFactory repoFactory = RepoFactory.getInstance();
    private static final OtherDependancies otherDependancies = OtherDependancies.getInstance();
    private static final ServiceFactory getInstance = new ServiceFactory();

    private final BookService bookService;
    private final MemberService memberService;
    private final PublisherService publisherService;
    private final AuthorService authorService;
    private ServiceFactory() {
        bookService = new BookServiceIMPL(otherDependancies.getMapper(),repoFactory.getRepo(RepoTypes.BOOK_REPO));
        memberService = new MemberServiceIMPL(otherDependancies.getMapper(),repoFactory.getRepo(RepoTypes.MEMBER_REPO));
        publisherService = new PublisherServiceIMPL(otherDependancies.getMapper(),repoFactory.getRepo(RepoTypes.PUBLISHER_REPO));
        authorService = new AuthorServiceIMPL(otherDependancies.getMapper(),repoFactory.getRepo(RepoTypes.AUTHOR_REPO));
    }

    public static ServiceFactory getInstance() {
        return getInstance;
    }

    public SuperService getService(ServiceType type) {
        switch (type){
            case BOOK_SERVICE:
                return  bookService;
            case MEMBER_SERVICE:
                return memberService;
            case PUBLISHER_SERVICE:
                return  publisherService;
            case AUTHOR_SERVICE:
                return  authorService;
            default:return null;
        }
    }
}
