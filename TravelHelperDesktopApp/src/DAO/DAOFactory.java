/*
 * Progetto di Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Realizzato da Ernesto De Crecchio - N86001596
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
        } else { // Altre implementazioni possibili diverse da AWS Elastic Beanstalk
            return new StructureDAOAWSElasticBeanstalkImpl();
        }
    }
    
    public static ReviewDAO getReviewDAO(String type) {
        if(type.equalsIgnoreCase("AWSElasticBeanstalk")) {
            return new ReviewDAOAWSElasticBeanstalkImpl();
        } else { // Altre implementazioni possibili diverse da AWS Elastic Beanstalk
            return new ReviewDAOAWSElasticBeanstalkImpl();
        }
    }
    
    public static UserDAO getUserDAO(String type) {
        if(type.equalsIgnoreCase("AWSElasticBeanstalk")) {
            return new UserDAOAWSElasticBeanstalkImpl();
        } else { // Altre implementazioni possibili diverse da AWS Elastic Beanstalk
            return new UserDAOAWSElasticBeanstalkImpl();
        }
    }
    
    public static AccessDAO getAccessDAO(String type) {
        if(type.equalsIgnoreCase("AWSElasticBeanstalk")) {
            return new AccessDAOAWSElasticBeanstalkImpl();
        } else { // Altre implementazioni possibili diverse da AWS Elastic Beanstalk
            return new AccessDAOAWSElasticBeanstalkImpl();
        }
    }
}
