    package gym.repo;

    import gym.data.interfaces.IDB;
    import gym.repo.interfaces.IMemberRepository;
    import gym.models.Member;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    public class MemberRepository implements IMemberRepository {

        private final IDB db;

        public MemberRepository(IDB db) {
            this.db = db;
        }


        @Override
        public void addMember(String name, String type, int months,double price ,Boolean active) {

            String sql = "INSERT INTO members(full_name, type, months, price,active) VALUES (?, ?, ?, ?,?)";

            try (PreparedStatement st = db.getConnection().prepareStatement(sql)) {
                st.setString(1, name);
                st.setString(2, type);
                st.setInt(3, months);
                st.setDouble(4, price);
                st.setBoolean(5,active);
                st.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public List<Member> getAllMembers() {
            List<Member> members = new ArrayList<>();
            String sql = "SELECT * FROM members ";

            try (Statement st = db.getConnection().createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                while (rs.next()) {
                    members.add(new Member(
                            rs.getInt("id"),
                            rs.getString("full_name"),
                            rs.getString("type"),
                            rs.getInt("months"),
                            rs.getDouble("price"),
                            rs.getInt("trainer_id"),
                            rs.getBoolean("active")
                    ));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return members;
        }
        @Override
        public List<Member> getActiveMembers() {
            List<Member> members = new ArrayList<>(); {
            String sql = "SELECT * FROM members WHERE active IS TRUE";

            try (Statement st = db.getConnection().createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                while (rs.next()) {
                    members.add(new Member(
                            rs.getInt("id"),
                            rs.getString("full_name"),
                            rs.getString("type"),
                            rs.getInt("months"),
                            rs.getDouble("price"),
                            rs.getInt("trainer_id"),
                            rs.getBoolean("active")
                    ));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
            return members;
        }
        @Override
        public Boolean deleteMember(int id) {
            String sql = "DELETE FROM members WHERE id = ?";
            try (PreparedStatement st = db.getConnection().prepareStatement(sql)) {
                st.setInt(1, id);
                return st.executeUpdate() > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }}
            @Override
            public void assignTrainer ( int memberId, int trainerId){
                String sql = "UPDATE members SET trainer_id=? WHERE id=?";
                try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
                    ps.setInt(1, trainerId);
                    ps.setInt(2, memberId);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
