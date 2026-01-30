package com.GymManagement.GymManagement.repository;

import com.GymManagement.GymManagement.model.Member;
import com.GymManagement.GymManagement.model.MemberDetailsDTO;

import java.util.List;

public interface MemberDAO {
    List<Member> getAllMembers();
    Member getMemberById(int id);
    void updateMember(Member member);
    void deleteMember(int id);
    public List<MemberDetailsDTO> getAllMemberDetails();
    void updateSubscriptionStatus(int memberId, boolean isActive);
    public void deleteMemberById(int memberId);

}

