//
//  UserDAOProtocol.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 25/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

protocol UserDAOProtocol {
    
    //func getByUsernameOrEmail(usernameOrEmail: String) -> User?
    
    func getByUsernameOrEmail(usernameOrEmail: String, completion: @escaping (User?) -> Void)
}
