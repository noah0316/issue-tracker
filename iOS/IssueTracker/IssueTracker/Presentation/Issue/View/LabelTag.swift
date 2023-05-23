//
//  LabelTag.swift
//  IssueTracker
//
//  Created by ilim on 2023/05/22.
//

import UIKit

final class BadgeLabel: UILabel {
    private let edgeInsets = UIEdgeInsets(top: 4.0, left: 16.0, bottom: 4.0, right: 16.0)
    
    override var intrinsicContentSize: CGSize {
        var contentSize = super.intrinsicContentSize
        contentSize.width += edgeInsets.left + edgeInsets.right
        contentSize.height += edgeInsets.top + edgeInsets.bottom
        return contentSize
    }
    
    convenience init(name: String) {
        self.init()
        self.text = name
        self.font = FontStyle.label
        self.textColor = .white
        self.setBackgroundColor()
        self.sizeToFit()
    }
    
    override func drawText(in rect: CGRect) {
        super.drawText(in: rect.inset(by: self.edgeInsets))
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        self.layer.cornerRadius = ComponentStyle.Radius.regular
        self.layer.masksToBounds = true
    }
    
    func setBackgroundColor() {
        self.backgroundColor = ColorStyle.issueTrackerBlue
    }
}
