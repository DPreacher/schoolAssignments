1./*
	Write a function that will count the SpaceStation with only one animal in the 
	AnimalList
*/
int countAnimal(){
	int countSingles = 0;
	
	SpaceStation *walker;
	assert(walker!=0);
	walker = firstPtr;
	
	while(walker!=NULL){
		if(walker->qty==1)
			countSingles++;
		walker=walker->link;
	}
	return countSingles;
}

2./*
	Write a function that will list all of the animals in the SpaceStations. Print
	the average number of animals living in a SpaceStation as well as the type of
	animal with the highest number of animals. You can assume that animals are
	unique per SpaceStation.
*/
void listAniamls(){
	int countStations=0,totalAnimals=0,avg;
	
	SpaceStation *walker;
	SpaceStation *highest;
	assert(walker!=Null);
	assert(highest!=0);
	
	walker=firstPtr;
	highest=walker;
	cout <<"Animal" <<setw(30)<<"Qty;
	while(walker!=NULL){
		cout << walker->animal << setw(30) << walker->qty; 
		totalAnimals+=walker->qty;
		if(highest->qty < walker->qty)
			highest=walker;
		countStations++;
		walker=walker->link;
	}
	if(countStations > 0)
		avg = totalAnimals/countStations;
		cout << "Average: " << avg << endl ;
		cout << "Most: " << highest->animal << " = " <<highest->qty;
	else
		cout << "No SpaceStations found.";
}
