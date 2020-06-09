//
//  Singleton.swift
//  DrenTravelApp
//
//  Created by Piero Junior Gaetani on 18/05/2020.
//  Copyright © 2020 Piero Junior Gaetani. All rights reserved.
//

import Foundation

final class singleton {

  // Can't init is singleton
    private init() { }
    
    // MARK: Shared Instance
    
    static let shared = singleton()
// MARK: Local Variable

var endpointGetToSearch : String = ""

}
