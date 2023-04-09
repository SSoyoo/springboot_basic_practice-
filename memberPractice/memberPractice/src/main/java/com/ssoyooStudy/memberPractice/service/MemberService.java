package com.ssoyooStudy.memberPractice.service;

import com.ssoyooStudy.memberPractice.Repository.MemberRepository;
import com.ssoyooStudy.memberPractice.dto.MemberDto;
import com.ssoyooStudy.memberPractice.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto) {

        //repository 의 save메서드 호출(조건: entity  객체를 넘겨줘야함)
        //1. dto객체를 entity로 변환
        //2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
        memberRepository.save(memberEntity);
    }

    //1. 회원이 입력한 이메일로 db에서 조회를 함
    //2. db에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단

    public MemberDto login(MemberDto memberDto) {

        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDto.getMemberEmail());


     /*
     1. 아이디가 존재하는지
     2. 비밀번호가 일치하는지
      */

        if (!byMemberEmail.isPresent()) return null;
        MemberEntity memberEntity = byMemberEmail.get();

        if (!memberEntity.getMemberPassword().equals(memberDto.getMemberPassword())) return null;

        MemberDto dto = MemberDto.toMemberDto(memberEntity);
        return dto;

        }
        public List<MemberDto> findAll(){
            List<MemberEntity> memberEntityList = memberRepository.findAll();
            List<MemberDto> memberDtoList = new ArrayList<>();
            for(MemberEntity memberEntity : memberEntityList){
                memberDtoList.add(MemberDto.toMemberDto(memberEntity));
            }
            return  memberDtoList;
        }


    public MemberDto findById(Long id) {


        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if(!optionalMemberEntity.isPresent()) return null;
        return MemberDto.toMemberDto(optionalMemberEntity.get());
    }

    public MemberDto updateForm(String myEmail){
        Optional<MemberEntity>optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if(!optionalMemberEntity.isPresent()) return null;
        return MemberDto.toMemberDto(optionalMemberEntity.get());
    }

    public void update(MemberDto memberDto){
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDto));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}