//
//  CountryViewModel.swift
//  iosApp
//
//  Created by SS on 12/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

enum CountryState {
    case loading
    case failed
    case loaded(countries: [Country_])
}

class CountryViewModel: ObservableObject {

    @Published
    private(set) var countryState: CountryState = .loading
    
    private let countryUseCase: LoadCountryUseCase
    
    init(countryUseCase: LoadCountryUseCase) {
        self.countryUseCase = countryUseCase
        fetchCountries()
    }
    
    func fetchCountries() {
        countryUseCase.invoke { (result : Result?, error : Error?) in
            if let successResult = result as? ResultSuccess<AnyObject> {
                let countries = successResult.data as? [Country_]
                self.countryState = .loaded(countries: countries ?? [])
            } else {
                self.countryState = .failed
            }
            
            if result is ResultError<AnyObject> {
                self.countryState = .failed
            }
        }
    }
    
}
