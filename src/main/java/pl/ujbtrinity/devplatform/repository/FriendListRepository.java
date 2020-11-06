package pl.ujbtrinity.devplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.ujbtrinity.devplatform.entity.FriendList;

import java.util.Set;

@Repository
public interface FriendListRepository extends JpaRepository<FriendList, Long> {

    @Query(value = "(SELECT invited_user FROM friend_list where status = 'FRIEND' and inviting_user = ?1)\n" +
            "UNION ALL\n" +
            "(SELECT inviting_user FROM friend_list where status = 'FRIEND' and invited_user = ?1)\n", nativeQuery = true)
    Set<Long> friendsCheck(Long id);

    @Query(value = "SELECT invited_user FROM friend_list where status = 'INVITATION' and inviting_user = ?1", nativeQuery = true)
    Set<Long> sentFriendRequests(Long id);

    @Query(value = "SELECT inviting_user FROM friend_list where status = 'INVITATION' and invited_user = ?1", nativeQuery = true)
    Set<Long> receivedFriendRequest(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM friend_list where status = 'FRIEND' and invited_user = ?1 and inviting_user = ?2", nativeQuery = true)
    void removeFromFriendList(Long invitedUserId, Long invitingUserId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM friend_list where status = 'INVITATION' and invited_user = ?1 and inviting_user = ?2", nativeQuery = true)
    void removeFriendRequest(Long invitedUserId, Long invitingUserId);



}
