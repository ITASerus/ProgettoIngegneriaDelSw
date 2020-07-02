//
//  AccessDAOProtocol.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 30/06/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

protocol AccessDAOProtocol {
    
    func logIn(usernameOrEmail: String, password: String, completion: @escaping ([String: Any]) -> Void)
    
}
