# SwipeAssignment
The project follows MVVM architecture. 
It has 2 screens, See Product and Add Product

Structure: 

  Remote(Network) -> RemoteDataSource             -> GetProductDetailsUseCase    ->                                -> ProductDetailsFragment
                                       -> Repo -> ->  PostProductUseCase          ->    ViewModel(SharedViewModel)
  Db(Database)    -> LocalDataSource              -> SearchProductDetailsUseCase ->                                ->  AddProductFragment

  **Data Flow: (Single Source of Truth = Database[Room])**
  
    Whenever the user opens the application, ProductDetails(Shows the details of the product) opens up and init{} of the viewmodel gets called, 
    -> ViewModel accesses the usecase(GetProductDetailsUseCase) and calls the repo method, whose task is to provide the data 
    -> Repo, is reponsible to fully handle the data response, it calls the network and adds the response to the Database while the fuction will return the value from the Database, parallely, to make sure that the screen will be populated with the data if the network is taking time response, as soon as there is addition to the database from the network, the database will emit the FLOW of data to the screen and UI will be updated.
    -> Database has method, getProductDetails() which returns a FLOW, Whenever there is change in the data of Database, it will emit the data and changes will get reflected over the screen.
    -> Data is coming from the Database, single source of truth will be database only, so the application will always support NO INTERNET AVAILABLE condition.
    -> Search Functionality is provided, by querying upon the DATABASE using ROOM(SearchProductDetailsUseCase). 
    -> The BottomSheetFragment(AddProductFragment) is being utilizing the same viewmodel and use PostProductUseCase to post the values and indicates the user by providing a TOAST to the user. 

**Viewmodel(ProductViewModel):**
    
   -> Inside Viewmodel the Coroutines are called upon IO thread. In order to make sure that the DB or REMOTE call is not happening over the MAIN thread and the UI will not become unreponsive in nature.
   -> StateFlow is being used here, in order to provide the values getting emiited from the Database
   -> Scopes of State flow is being handled by the Couroutines scope(lifeCycleScope), in order to stop the flow from collecting the values, when the screen is not in RESUME state. 
   -> The data is being wrapped against the RESOURCE class(SEALED CLASS), which emits the generic response in form of SUCCESS, LOADING and ERROR

   **NETWORK CONNECTIVITY**
   -> Network connectivity indication is being handled by using Connectivity Manager along with LiveData, the Livedata will provide the reponse of the current network state, and is being observed inside the MAIN ACTIVITY 
   -> It indicates the user about the current network state using the SNACK BAR. 
