//
//  ShadowRoundedImageView.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 13/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class ShadowRoundedImageView: UIView {
    @IBInspectable var image: UIImage? = nil {
        didSet {
            imageLayer.contents = image?.cgImage
            shadowLayer.shadowPath = (image == nil) ? nil : shapeAsPath
        }
    }
    
    var imageLayer: CALayer = CALayer()
    var filterLayer: CALayer = CALayer()
    var shadowLayer: CALayer = CALayer()
    
    @IBInspectable
    var cornerRadius: CGFloat {
        get { return layer.cornerRadius }
        set { layer.cornerRadius = newValue }
    }
    
    @IBInspectable
    var filterEnabled: Bool {
        get { return !filterLayer.isHidden }
        set { filterLayer.isHidden = !newValue}
    }
    
    var shape: UIBezierPath {
        return UIBezierPath(roundedRect: bounds, cornerRadius: cornerRadius)
    }
    
    var shapeAsPath: CGPath {
        return shape.cgPath
    }
    
    var shapeAsMask: CAShapeLayer {
        let s = CAShapeLayer()
        s.path = shapeAsPath
        return s
    }
    
    @IBInspectable
    var shadowOpacity: Float {
        get { return shadowLayer.shadowOpacity }
        set { shadowLayer.shadowOpacity = newValue }
    }
    
    @IBInspectable
    var shadowRadius: CGFloat {
        get { return shadowLayer.shadowRadius }
        set { shadowLayer.shadowRadius = newValue }
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        clipsToBounds = false
        backgroundColor = .clear
        
        self.layer.addSublayer(shadowLayer)
        
        self.layer.addSublayer(imageLayer) // (in that order)
        self.layer.addSublayer(filterLayer)
        
        imageLayer.frame = bounds
        imageLayer.contentsGravity = .resizeAspectFill // (as preferred)
        
        filterLayer.frame = bounds
        filterLayer.backgroundColor = CGColor.init(srgbRed:  CGFloat(arc4random_uniform(256)) / 255.0, green:  CGFloat(arc4random_uniform(256)) / 255.0, blue:  CGFloat(arc4random_uniform(256)) / 255.0, alpha: 0.2)
        filterLayer.contentsGravity = .resizeAspectFill
        
        imageLayer.mask = shapeAsMask
        filterLayer.mask = shapeAsMask
        shadowLayer.shadowPath = (image == nil) ? nil : shapeAsPath
        //shadowLayer.shadowOpacity = 0.5
        //shadowLayer.shadowRadius = 3
        shadowLayer.shadowOffset = CGSize.zero
    }
}
