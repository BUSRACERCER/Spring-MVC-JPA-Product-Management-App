package com.works.mvcdata.services;

import com.works.mvcdata.entities.Product;
import com.works.mvcdata.utils.DB;
import com.works.mvcdata.utils.DBJPA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchService {

    public List<Product> products(int p) {
        List<Product> ls = new ArrayList<>();
        DBJPA dbjpa = new DBJPA();
        try {
            p = p - 1;
            p = p * 3;
            String sql = "select * from product  order by pid desc limit ?,3 ";
            PreparedStatement pre = dbjpa.connect().prepareStatement(sql);
            pre.setInt(1, p);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product u = new Product();
                u.setPid(rs.getLong("pid"));
                u.setDetail(rs.getString("detail"));
                u.setPrice(rs.getInt("price"));
                u.setTitle(rs.getString("title"));
                u.setStock(rs.getInt("stock"));
                u.setCid(rs.getLong("cid"));
                ls.add(u);
            }

        } catch (Exception ex) {
            System.err.println("Search Product Pagination ERROR :" + ex);
        } finally {
            dbjpa.close();
        }
        return ls;
    }

    public int totalCount() {
        int count = 0;
        DBJPA dbjpa = new DBJPA();
        try {
            String sql = "select  count(pid) as count from product ";
            PreparedStatement pre = dbjpa.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                count = rs.getInt("count");

            }

        } catch (Exception ex) {
            System.err.println("Pagination Error : " + ex);

        } finally {
            dbjpa.close();
        }
        return count;
    }

    public List<Product> search(String q) {
        List<Product> ls = new ArrayList<>();

        DBJPA dbjpa = new DBJPA();
        try {
            String sql = "select *from  product where  title like  ? or detail like ? or price like  ?";
            PreparedStatement pre = dbjpa.connect().prepareStatement(sql);

            pre.setString(1, "%" + q + '%');
            pre.setString(2, '%' + q + '%');
            pre.setString(3, '%' + q + '%');
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product u = new Product();
                u.setPid(rs.getLong("pid"));
                u.setDetail(rs.getString("detail"));
                u.setPrice(rs.getInt("price"));
                u.setTitle(rs.getString("title"));
                u.setStock(rs.getInt("stock"));
                u.setCid(rs.getLong("cid"));
                ls.add(u);
            }


        } catch (Exception ex) {
            System.err.println("Product Search Error : " + ex);

        } finally {
            dbjpa.close();
        }
        return ls;
    }

    public Product single(int pid) {
        DBJPA dbjpa = new DBJPA();
        Product u = new Product();
        try {
            String sql = "select * from product where pid =?";
            PreparedStatement pre = dbjpa.connect().prepareStatement(sql);
            pre.setLong(1, pid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                u.setPid(rs.getLong("pid"));
                u.setDetail(rs.getString("detail"));
                u.setPrice(rs.getInt("price"));
                u.setTitle(rs.getString("title"));
                u.setStock(rs.getInt("stock"));
                u.setCid(rs.getLong("cid"));


            }

        } catch (Exception ex) {
            System.err.println("User Info Error : " + ex);

        } finally {
            dbjpa.close();
        }
        return u;

    }

    public int deleteImage(int pid) {
        int status = 0;
        DBJPA dbjpa = new DBJPA();
        try {
            //String sql="delete from users where uid=?";
            String sql = "update product set  where pid=?";
            PreparedStatement pre = dbjpa.connect().prepareStatement(sql);

            pre.setInt(1, pid);
            status = pre.executeUpdate();

        } catch (Exception ex) {
            System.err.println("Users Error :" + ex);
        } finally {
            dbjpa.close();
        }
        return status;

    }
}
