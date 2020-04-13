/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author ernestodecrecchio
 */
public class DAOFactory {
    
    public static StructureDAO getStructureDAO(String type) {
        if(type.equalsIgnoreCase("AWSElasticBeanstalk")) {
            return new StructureDAOAWSElasticBeanstalkImpl();
        } else {
            return new StructureDAOAWSElasticBeanstalkImpl();
        }
    }
    
    public static ReviewDAO getReviewDAO(String type) {
        if(type.equalsIgnoreCase("AWSElasticBeanstalk")) {
            return new ReviewDAOAWSElasticBeanstalkImpl();
        } else {
            return new ReviewDAOAWSElasticBeanstalkImpl();
        }
    }
    
    public static UserDAO getUserDAO(String type) {
        if(type.equalsIgnoreCase("AWSElasticBeanstalk")) {
            return new UserDAOAWSElasticBeanstalkImpl();
        } else {
            return new UserDAOAWSElasticBeanstalkImpl();
        }
    }
}
