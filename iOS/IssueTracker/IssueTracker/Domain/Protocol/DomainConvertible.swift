//
//  DomainConvertible.swift
//  IssueTracker
//
//  Created by Noah on 2023/06/01.
//

import Foundation

protocol DomainConvertible {
    associatedtype DomainType
    
    func toDomain() -> DomainType
}
