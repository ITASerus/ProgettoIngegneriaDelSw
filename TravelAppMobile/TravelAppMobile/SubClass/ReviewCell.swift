//
//  ReviewCell.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 20/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class ReviewCell: UITableViewCell {

//    override func awakeFromNib() {
//        super.awakeFromNib()
//        // Initialization code
//    }
//
//    override func setSelected(_ selected: Bool, animated: Bool) {
//        super.setSelected(selected, animated: animated)
//
//        // Configure the view for the selected state
//    }
    
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var bodyLabel: UILabel!
    @IBOutlet weak var authorLabel: UILabel!
    @IBOutlet weak var pointsImageView: UIImageView!
    @IBOutlet weak var profilePictureImageView: UIImageView!
    @IBOutlet weak var backgroundImageView: UIImageView!
}
