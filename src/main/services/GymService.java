package com.GymManagement.GymManagement.service;

import com.GymManagement.GymManagement.model.Member;
import com.GymManagement.GymManagement.model.Subscription;
import com.GymManagement.GymManagement.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GymService {

    @Autowired
    private GymRepository repository;

    public Member login(String email, String password) {
        Member member = repository.findByEmail(email);
        if (member != null && member.getPassword().equals(password)) {
            return member;
        }
        return null;
    }

//    public Subscription getMemberSubscription(int memberId) {
//        return repository.getSubscriptionByMemberId(memberId);
//    }
    public Subscription getMemberSubscription(int memberId) {
        return repository.getLatestSubscriptionByMemberId(memberId);
    }


    public void addSubscription(Subscription subscription) {
        repository.saveSubscription(subscription);
    }

    public void registerMember(Member member) {
        repository.saveMember(member);
    }

    public boolean memberExists(String email) {
        return repository.findByEmail(email) != null;
    }

//    public void saveSubscription(Subscription sub) {
//        repository.saveSubscription(sub);
//    }

}
