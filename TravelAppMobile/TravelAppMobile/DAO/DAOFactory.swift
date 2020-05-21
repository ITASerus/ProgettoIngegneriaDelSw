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
    
}
