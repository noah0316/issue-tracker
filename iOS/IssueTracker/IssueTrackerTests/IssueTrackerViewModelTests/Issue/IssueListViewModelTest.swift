//
//  IssueListViewModelTest.swift
//  IssueTrackerTests
//
//  Created by Noah on 2023/05/24.
//

import Combine
import XCTest

final class IssueListViewModelTest: XCTestCase {
    private var viewModel: IssueListViewModel!
    private var mockUseCase: MockIssueListUseCase!
    private var cancellables: Set<AnyCancellable>!

    override func setUpWithError() throws {
        self.mockUseCase = MockIssueListUseCase()
        self.viewModel = IssueListViewModel(useCase: mockUseCase)
        self.cancellables = Set<AnyCancellable>()
        try super.setUpWithError()
    }

    override func tearDownWithError() throws {
        self.mockUseCase = nil
        self.viewModel = nil
        self.cancellables = nil
        try super.tearDownWithError()
    }

    func test_viewDidLoad가_호출되었을때_이슈목록을조회하는가() {
        // Arrange
        let issuesExpectation = XCTestExpectation(description: "Received issues")
        var isFetchIssueListCalled: Bool?
        
        self.viewModel.output
            .issues
            .sink { _ in
                return
            } receiveValue: { [weak self] _ in
                isFetchIssueListCalled = self?.mockUseCase.isFetchIssueListCalled
                issuesExpectation.fulfill()
            }
            .store(in: &self.cancellables)
        
        // Act
        self.viewModel.input.viewDidLoad()
        
        // Assert
        wait(for: [issuesExpectation], timeout: 1.0)
        XCTAssertEqual(isFetchIssueListCalled, true)
    }
    
    func test_이슈목록조회에_성공하였을때_로딩완료상태가되는가() {
        // Arrange
        let loadingStatusExpectation = XCTestExpectation(description: "Received loading status")
        let issuesExpectation = XCTestExpectation(description: "Received issues")
        var receivedLoadingStatus: LoadingStatus?
        
        self.viewModel.output
            .issueListLoadingStatus
            .sink { _ in
                return
            } receiveValue: { issueListLoadingStatus in
                receivedLoadingStatus = issueListLoadingStatus
                loadingStatusExpectation.fulfill()
            }
            .store(in: &self.cancellables)
        
        self.viewModel.output
            .issues
            .sink { _ in
                return
            } receiveValue: { _ in
                issuesExpectation.fulfill()
            }
            .store(in: &self.cancellables)

        // Act
        self.viewModel.input.viewDidLoad()
        
        // Assert
        wait(for: [loadingStatusExpectation, issuesExpectation ], timeout: 1.0)
        XCTAssertEqual(receivedLoadingStatus, .done)
    }
}
