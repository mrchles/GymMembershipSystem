    package gym.repo;

    import gym.data.interfaces.IDB;
    import gym.repo.interfaces.IMemberRepository;

    import java.sql.*;

    public class MemberRepository implements IMemberRepository {

        private final IDB db;

        public MemberRepository(IDB db) {
            this.db = db;
        }



        @Override
        public void addMember(String name, String type, int months,Boolean active) {
            double price;

            if (type.equalsIgnoreCase("premium")) {
                price = 15000 * months;
            } else if (type.equalsIgnoreCase("VIP")) {
                price = 25000 * months;
            } else { // STANDARD
                price = 10000 * months;
            }

            String sql = "INSERT INTO members(full_name, type, months, price,active) VALUES (?, ?, ?, ?,?)";

            try (PreparedStatement st = db.getConnection().prepareStatement(sql)) {
                st.setString(1, name);
                st.setString(2, type);
                st.setInt(3, months);
                st.setDouble(4, price);
                st.setBoolean(5,active);
                st.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public void showAll() {
            String sql = "SELECT * FROM members ";

            try (Statement st = db.getConnection().createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                while (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + ". " +
                                    rs.getString("full_name") +
                                    " | " + rs.getString("type") +
                                    " | months: " + rs.getInt("months") +
                                    " | price: " + rs.getDouble("price") +
                                    " | trainer ID : "+ rs.getObject("trainer_id")
                    );
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        @Override
        public void showActiveMemberships() {
            String sql = "SELECT * FROM members WHERE active IS TRUE";;

            try (Statement st = db.getConnection().createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                System.out.println("active members:");
                while (rs.next()) {
                    System.out.println(
                            rs.getInt("id") +
                                    ". " + rs.getString("full_name") +
                                    " | " + rs.getString("type") +
                                    " | months: " + rs.getInt("months") +
                                    " | price: " + rs.getDouble("price") +
                                    " | trainer ID : "+ rs.getObject("trainer_id")
                    );
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public void deleteMember(int id) {
            String sql = "DELETE FROM members WHERE id = ?";
            try (PreparedStatement st = db.getConnection().prepareStatement(sql)) {
                st.setInt(1, id);
                int rows = st.executeUpdate();

                if (rows > 0) {
                    System.out.println("Member deleted successfully.");
                } else {
                    System.out.println("Member not found.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } public void assignTrainer(int memberId, int trainerId) {
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
