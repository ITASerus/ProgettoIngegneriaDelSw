//
//  UserDAOAWSElasticBeanstalkImpl.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 25/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

public class UserDAOAWSElasticbeanstalkImpl: UserDAOProtocol {
    private let GET_BY_USERNAME_OR_EMAIL = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/users/usernameOrEmail/"
    
    /*func getByUsernameOrEmail(usernameOrEmail: String) -> User? {
        let FINAL_URL = GET_BY_USERNAME_OR_EMAIL + "username="+usernameOrEmail+"&email="+usernameOrEmail
        
        let dataStructures = try! Data.init(contentsOf: URL.init(string: FINAL_URL)!)
        
        do {
            let decoder: JSONDecoder = JSONDecoder.init()
            let user: User = try decoder.decode(User.self, from: dataStructures)
                        
            return user
        } catch let e {
            print(e)
        }

        return nil
    }*/
    
    func getByUsernameOrEmail(usernameOrEmail: String, completion: @escaping (User?) -> Void) {
        let FINAL_URL = GET_BY_USERNAME_OR_EMAIL + "username="+usernameOrEmail+"&email="+usernameOrEmail
        
        let dataStructures = try! Data.init(contentsOf: URL.init(string: FINAL_URL)!)
        
        do {
            let decoder: JSONDecoder = JSONDecoder.init()
            let user: User = try decoder.decode(User.self, from: dataStructures)
            
            completion(user)
            
        } catch let e {
            print(e)
        }
    }
   
}
