package org.example.library_management_system.service;

import org.example.library_management_system.dto.MemberDTO;

import org.example.library_management_system.entity.Member;
import org.example.library_management_system.repo.MemberRepo;
import org.example.library_management_system.util.exceptions.MemberException;

import java.sql.SQLException;
import java.util.Optional;

public class MemberService {

    private  final MemberRepo memberRepo = new MemberRepo();

    public boolean addMember(MemberDTO member) throws MemberException {
        Member entity = this.covnertDTOtoEntity(member);
        try {
            boolean isSaved = memberRepo.save(entity);
            return isSaved;
        } catch (SQLException | ClassNotFoundException e) {
            if (e instanceof SQLException) {
                if (((SQLException) e).getErrorCode() == 1062) {
                    throw new MemberException("ID already exists-cannot Save ");
                } else if (((SQLException) e).getErrorCode() == 1406) {
                   String message = ((SQLException) e).getMessage();
                   String[] s = message.split("'");
                   throw new MemberException("Data is To Large For "+s[1]);
                }
            }
            e.printStackTrace();
            throw new MemberException("Error in saving member", e);
        }

    }


    public boolean delete(String id) throws MemberException {
        try {
            boolean delete = memberRepo.delete(id);
            return delete;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new MemberException("Failed to delete member", e);
        }


    }

public boolean update(MemberDTO member) throws MemberException {
    Member entity = covnertDTOtoEntity(member);
    try {
        boolean isUpdated = memberRepo.update(entity);
        return isUpdated;
    } catch (SQLException | ClassNotFoundException e) {
        if (e instanceof SQLException) {
            if (((SQLException) e).getErrorCode() == 1406) {
                String message = ((SQLException) e).getMessage();
                String[] s = message.split("'");
                throw new MemberException("Data is To Large For "+s[1]);

            }
        }
        throw new MemberException("Error in updating member", e);
    }
}


public Optional<MemberDTO> search(String id){
    try {
        Optional<Member> member = memberRepo.searchMember(id);
        if (member.isPresent()) {
            MemberDTO memberDTO = covnertEntityToDTO(member.get());
            return Optional.of(memberDTO);
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    return Optional.empty();
}

   private Member covnertDTOtoEntity(MemberDTO memberDTO) {
       Member member = new Member();
       member.setId(memberDTO.getId());
       member.setName(memberDTO.getName());
       member.setAddress(memberDTO.getAddress());
       member.setEmail(memberDTO.getEmail());
       member.setContact(memberDTO.getMobileNumber());
       return member;
    }

    private MemberDTO covnertEntityToDTO(Member memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setName(memberEntity.getName());
        memberDTO.setAddress(memberEntity.getAddress());
        memberDTO.setEmail(memberEntity.getEmail());
        memberDTO.setMobileNumber(memberEntity.getContact());
        return memberDTO;
    }
}

