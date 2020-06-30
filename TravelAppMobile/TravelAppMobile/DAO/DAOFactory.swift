//
//  DAOFactory.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 21/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

public class DAOFactory {
    
    static func getStructureDAO(type: String) -> StructureDAOProtocol {
        if type == "AWSElasticBeanstalk" {
            return StructureDAOAWSElasticbeanstalkImpl()
        } else { // Altre implementazioni possibili diverse da AWS Elastic Beanstalk
            return StructureDAOAWSElasticbeanstalkImpl()
        }
    }
    
    static func getUserDAO(type: String) -> UserDAOProtocol {
        if type == "AWSElasticBeanstalk" {
            return UserDAOAWSElasticbeanstalkImpl()
        } else { // Altre implementazioni possibili diverse da AWS Elastic Beanstalk
            return UserDAOAWSElasticbeanstalkImpl()
        }
    }
    
    static func getReviewDAO(type: String) -> ReviewDAOProtocol {
        if type == "AWSElasticBeanstalk" {
            return ReviewDAOAWSElasticbeanstalkImpl()
        } else { // Altre implementazioni possibili diverse da AWS Elastic Beanstalk
            return ReviewDAOAWSElasticbeanstalkImpl()
        }
    }
    
    static func getAccessDAO(type: String) -> AccessDAOProtocol {
        if type == "AWSElasticBeanstalk" {
            return AccessDAOAWSElasticbeanstalkImpl()
        } else { // Altre implementazioni possibili diverse da AWS Elastic Beanstalk
            return AccessDAOAWSElasticbeanstalkImpl()
        }
    }
    
}
