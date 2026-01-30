package com.GymManagement.GymManagement.repository;

import com.GymManagement.GymManagement.model.Member;
import com.GymManagement.GymManagement.model.MemberDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Member> getAllMembers() {
        String sql = "SELECT * FROM members";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class));
    }

    @Override
    public Member getMemberById(int id) {
        String sql = "SELECT * FROM members WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Member.class), id);
    }

    @Override
    public void updateMember(Member member) {
        String sql = "UPDATE members SET name = ?, email = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, member.getName(), member.getEmail(), member.getPassword(), member.getMemberId());
    }

    @Override
    public void deleteMember(int id) {
        String sql = "DELETE FROM members WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateSubscriptionStatus(int memberId, boolean isActive) {
        String sql = "UPDATE subscriptions SET is_active = ? WHERE member_id = ?";
        jdbcTemplate.update(sql, isActive, memberId);
    }


    @Override
    public List<MemberDetailsDTO> getAllMemberDetails() {
        String sql = """
        SELECT 
            m.id AS member_id,
            m.name,
            m.email,
            s.start_month,
            s.end_month,
            s.is_active,
            sl.slot_time
        FROM subscriptions s
        JOIN members m ON s.member_id = m.id
        JOIN slots sl ON s.slot_id = sl.id
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            MemberDetailsDTO dto = new MemberDetailsDTO();
            dto.setMemberId(rs.getInt("member_id"));
            dto.setName(rs.getString("name"));
            dto.setEmail(rs.getString("email"));
            dto.setStartMonth(rs.getString("start_month"));
            dto.setEndMonth(rs.getString("end_month"));
            dto.setIsActive(rs.getBoolean("is_active"));
            dto.setSlotTime(rs.getString("slot_time"));
            return dto;
        });
    }

    @Override
    public void deleteMemberById(int memberId) {
        // Delete subscriptions first (to avoid foreign key constraint violation)
        String deleteSubscriptions = "DELETE FROM subscriptions WHERE member_id = ?";
        jdbcTemplate.update(deleteSubscriptions, memberId);

        // Then delete the member
        String deleteMember = "DELETE FROM members WHERE id = ?";
        jdbcTemplate.update(deleteMember, memberId);
    }

}

