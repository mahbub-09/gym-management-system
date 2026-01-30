package com.GymManagement.GymManagement.repository;

import com.GymManagement.GymManagement.model.Member;
import com.GymManagement.GymManagement.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GymRepository {

    public static final String GET_MEMBER_BY_EMAIL = "SELECT * FROM members WHERE email = ?";
    public static final String INSERT_INTO_MEMBER = "INSERT INTO members (name, email, password) VALUES (?, ?, ?)";
    public static final String INSERT_INTO_SUBSCRIPTION = "INSERT INTO subscriptions (member_id, slot_id, start_month, end_month) VALUES (?, ?, ?, ?)";
    public static final String GET_MEMBER_BY_ID_FROM_SUBSCRIPTION = "SELECT * FROM subscriptions WHERE member_id = ? ORDER BY id DESC LIMIT 1";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveMember(Member member) {
        //String sql = "INSERT INTO members (name, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(INSERT_INTO_MEMBER, member.getName(), member.getEmail(), member.getPassword());
    }


//    public Member findByEmail(String email) {
//        String sql = "SELECT * FROM members WHERE email = ?";
//        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Member.class), email);
//    }

    public Member findByEmail(String email) {
        //String sql = "SELECT * FROM members WHERE email = ?";
        List<Member> members = jdbcTemplate.query(GET_MEMBER_BY_EMAIL, new BeanPropertyRowMapper<>(Member.class), email);
        return members.isEmpty() ? null : members.get(0);
    }


//    public Subscription getSubscriptionByMemberId(int memberId) {
//        String sql = "SELECT * FROM subscriptions WHERE member_id = ?";
//        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Subscription.class), memberId);
//    }

//    public Subscription getSubscriptionByMemberId(int memberId) {
//        String sql = "SELECT * FROM subscriptions WHERE member_id = ?";
//        List<Subscription> subscriptions = jdbcTemplate.query(
//                sql,
//                new BeanPropertyRowMapper<>(Subscription.class),
//                memberId
//        );
//        return subscriptions.isEmpty() ? null : subscriptions.get(0);
//    }

    //Good
    public Subscription getLatestSubscriptionByMemberId(int memberId) {
        //String sql = "SELECT * FROM subscriptions WHERE member_id = ? ORDER BY id DESC LIMIT 1";
        List<Subscription> subscriptions = jdbcTemplate.query(
                GET_MEMBER_BY_ID_FROM_SUBSCRIPTION,
                new BeanPropertyRowMapper<>(Subscription.class),
                memberId
        );
        return subscriptions.isEmpty() ? null : subscriptions.get(0);
    }


    public void saveSubscription(Subscription subscription) {
        //String sql = "INSERT INTO subscriptions (member_id, slot_id, start_month, end_month) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(INSERT_INTO_SUBSCRIPTION,
                subscription.getMemberId(),
                subscription.getSlotId(),
                subscription.getStartMonth(),
                subscription.getEndMonth());
    }

}