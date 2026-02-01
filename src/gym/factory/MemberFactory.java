package gym.factory;

import gym.models.Member;
import gym.models.SubCategory;
import gym.models.Subscription;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberFactory {

    public static Member createMemberFromResultSet(ResultSet rs) throws SQLException {
        String catStr = rs.getString("sub_category");
        SubCategory subCat;
        if ("base".equalsIgnoreCase(catStr)) subCat = SubCategory.base;
        else if ("premium".equalsIgnoreCase(catStr)) subCat = SubCategory.premium;
        else subCat = SubCategory.elite;

        Subscription sub = new Subscription(
                rs.getInt("sub_id"),
                rs.getString("sub_name"),
                rs.getInt("price_per_month"),
                subCat
        );


        Member.TrainerInfo trainer = null;
        int trainerId = rs.getInt("trainer_id");
        if (!rs.wasNull()) {
            trainer = new Member.TrainerInfo(
                    trainerId,
                    rs.getString("trainer_name"),
                    rs.getString("trainer_specialization")
            );
        }

        return new Member.Builder()
                .id(rs.getInt("id"))
                .fullName(rs.getString("full_name"))
                .months(rs.getInt("months"))
                .price(rs.getDouble("price"))
                .active(rs.getBoolean("active"))
                .trainer(trainer)
                .subscription(sub)
                .build();
    }
}
