drop table LesTickets ; 
drop table LesDossiers;
drop table LesRepresentations ; 
drop table LesSpectacles ; 
drop table LesPlaces ; 
drop table LesZones ; 
drop table LesCategories ; 
 
create table LesCategories (nomC varchar2(20), prix number (8,2), 
      constraint cat_c1 primary key (nomC), 
      constraint cat_c2 check (prix > 0),            
	  constraint cat_c3 check (nomC ='orchestre' or nomC ='balcon' or nomC = 'poulailler')
) ; 
create table LesZones (numZ number (4), nomC varchar2(20), 
      constraint zones_c1 primary key (numZ), 
      constraint zones_c2 foreign key (nomC) references LesCategories (nomC),
      constraint zones_c3 check (numZ > 0)         
) ; 
create table LesPlaces (noPlace number (4), noRang number (4), numZ number (4), 
      constraint places_c1 primary key (noPlace, noRang), 
      constraint places_c2 foreign key (numZ) references LesZones (numZ),
      constraint places_c3 check (noRang > 0),   
      constraint places_c4 check (noPlace > 0),
      constraint places_c5 check (numZ > 0)
     
)  ; 
create table LesSpectacles (numS number (4), nomS varchar2(40), 
      constraint spec_c1 primary key (numS),   
      constraint spec_c2 check (numS > 0) 
) ; 
create table LesRepresentations (numS number (4), dateRep date,
      constraint rep_c1 primary key (numS, dateRep),
      constraint rep_c2 foreign key (numS) references LesSpectacles (numS),   
      constraint rep_c3 check (numS > 0) 
) ; 
create table LesDossiers (noDossier number (4), montant number (6,2),
      constraint dossiers_c1 primary key (noDossier),
      constraint dossiers_c2 check (montant > 0),
      constraint dossiers_c3 check (noDossier > 0)
);

create table LesTickets (noSerie number (4), numS number (4),  
      dateRep date, noPlace number (4), noRang number (4), 
      dateEmission date, noDossier number (4), 
      constraint tickets_c1 primary key (noSerie), 
      constraint tickets_c2 primarey key (numS,dateRep,noPlace,noRang),
      constraint tickets_c3 foreign key (numS,dateRep)  
                 references LesRepresentations (numS,dateRep), 
      constraint tickets_c4 foreign key (noPlace,noRang)  
                 references LesPlaces (noPlace,noRang),
      constraint tickets_c6 foreign key (noDossier)  
                 references LesDossiers (noDossier),
      constraint tickets_c7 check (numS >0),
      constraint tickets_c8 check (noPlace >0),
      constraint tickets_c9 check (noRang >0),
      constraint tickets_c10 check (noDossier >0),      
      constraint tickets_c11 check (dateEmission < dateRep)
      
) ; 
