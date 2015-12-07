#include <ostream>
#include <iostream>
#include <iomanip>
#include <string>
#include <cassert>
#include "datelist.h";
using namespace std;

DateList::DateList(string newName){
	datingServiceName = newName.length() == 0 ? "Invalid Name" : newName;
	numberOfDates = 0;
	firstPtr = NULL;
}

void DateList::showDate(ostream  & out)const{
	out << datingServiceName << endl;
	out << left << setw(25) << "Name" << setw(8) << "Gender" << setw(8) << "Age" << endl << endl;

	Date *walker = firstPtr;
	while (walker != NULL){
		out << setw(25) << walker->name << setw(8) << walker->gender << setw(8) << walker->age;
		walker = walker->link;

	}
	out << "# of possible dates=" << numberOfDates << endl;
}
void DateList::addDate(){
	
	string newName;
	cout << "Enter Name: ";
	getline(cin, newName);
	
	Date *walker = firstPtr, *stalker=NULL;

	while (walker != NULL){
		
		if (newName == walker->name)
			break;
		stalker = walker; 
		walker = walker->link;
	}
	if (walker== NULL){
		char gender;
		int age;
		Date *builder = new Date;
		assert(builder != NULL);

		cout << "Enter Age: ";
		cin >> age;
		cout << "Enter gender (m/f): ";
		cin >> gender;
		builder->age = age >= 18 ? age : 18;
		builder->gender = toupper(gender == 'm' || gender == 'M' || gender == 'f' || gender == 'F' ? gender : 'f');
		builder->name = newName.length() > 0 ? newName : "invalid Name";
		if (firstPtr != NULL)
			stalker->link = builder;
		else
			firstPtr = builder;
		numberOfDates++;
	}	
	cin.ignore(80, '\n');
	cin.clear();
}
