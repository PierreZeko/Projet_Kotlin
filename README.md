# Projet Kotlin Chuck Norris Jokes API

Ce projet est découpé en 4 grandes parties, elles-mêmes découpées en sous-parties.
Ce"README" a pour but d'en expliquer le contenu.
Ce projet a lieu dans le cadre de l'unité OUAP 4331 : Android Development For Smartphones With Kotlin.
Il est supervisé par M. Duponchel

------------------------------------------------------------------------------------

# I - Create a UI List component


## 1. Create a static list of jokes
Pour commencer ce projet, il faut créer un Objet qui contiendra un ensemble de 10 jokes sur Chuck Norris.
Ces jokes sont récupérées sur le site suivant: https://api.chucknorris.io/

Une fois la liste de jokes créée, on l'affiche dans le Logcat afin de vérifier qu'il n'y a pas de potentielles erreurs.

## 2. RecyclerView instantiation
Afin d'avoir sa liste de jokes dans l'application, nous utilisons une RecyclerView. 
Pour utiliser cette view, il est d'abord nécessaire d'ajouter les dépendances dans le fichier "build.gradle (app)".

Puis, dans le fichier xml principal (fichier Layout), on ajoute une nouvelle RecyclerView qui doit par la suite être liée à l'activité principale.
La RecyclerView doit également être configurée afin d'être utilisée.

Enfin, il faut utiliser un Adapteur pour gérer le contenu de la RecyclerView. Nous passons donc à la prochaine partie.

## 3. Custom Adapter
Afin d'obtenir l'adapteur de la RecyclerView, il faut d'abord créer une nouvelle classe "JokeAdapter".
Cette classe contient alors la liste de jokes qui seront affichées. De plus, on y ajoute une classe interne "JokeAdapter.JokeViewHolder" qui hérite de "RecyclerView.ViewHolder".
Cette classe interne contient seulement une TextView qui permettra d'afficher les jokes à l'écran.

Il faut ensuite Override plusieurs méthodes: 
- getItemCount() qui permet de connaître le nombre de jokes 
- onBindViewHolder(..) qui permet de donner la nouvelle joke à afficher
- onCreateViewHolder(..) qui permet de créer la TextView qui sera utilisée dans la classe interne pour ensuite être affichée

On ajoute également un appel à la méthode "notifyDataSetChanged()" qui sert à prévenir que le contenu de notre liste a évolué.

## 4. Custom view for items
Maintenant que l'on peut afficher notre liste de jokes à l'écran, il est intéressant de s'occuper du design de l'interface.
En créant un nouveau fichier xml "joke_layout.xml" contenant simplement une TextView, on peut alors personnaliser entièrement l'affichage des jokes.
On peut par exemple modifier la police, l'emplacement des jokes, l'écart entre celles-ci, la couleur des jokes, l'arrière-plan et autres.

Seulement, pour que ces configurations soient prises en compte, il est nécessaire d'en informer la View qui s'occupe d'afficher les jokes.
On remplace alors la TextView utilisée dans la classe "JokeAdapter" par notre fichier xml à l'aide d'un LayoutInflater.

## 5. Conclusion
À ce stade, nous avons une RecyclerView avec son adapteur qui nous permet d'afficher la liste de jokes en les personnalisant selon notre volonté.

---------------------------------------------------------------------------------------------------------------------------------------

# II - Fetch jokes


## 1. Create the data model matching the API
Dans cette partie, nous allons utiliser la Serialization (on code une information longue en un ensemble de plus petites informations) pour récupérer les jokes directement depuis l'API.
Il est donc nécessaire d'importer les librairies correspondantes.

Ensuite, il faut créer une classe data "Joke" qui sera @Serializable.
Elle contiendra plusieurs informations telles que l'url de la joke, sa valeur ou encore sa date de création.

On utilise alors le fichier "JokeSerializationTest.kt" qui nous est fourni afin de vérifier que tout a été correctement configuré.
Il pourra également servir plus tard et nous verrons de quelle manière.

## 2. Import Retrofit & Rx Java
Pour continuer, il faut importer les différentes librairies de Retrofit.

Il faut également importer RxJava2, un adaptateur pour que Rx et Retrofit fonctionnent ainsi qu'un convertisseur pour que Retrofit fonctionne avec la serialization.

Enfin, étant donné qu'on aura besoin d'internet pour récupérer les jokes sur le site de l'API, il faut ajouter les permissions d'accès à internet depuis l'application.

## 3. Retrofit usage
Pour continuer dans notre projet, il faut ajouter une nouvelle interface "JokeApiService".

Cette interface doit contenir une fonction qui permettra d'obtenir une Single<Joke>.
De plus, il est important de rajouter l'annotation "@GET(..)" pour faire comprendre qu'il s'agit d'un getter http.

Maintenant on crée un objet de JokeApiService.
Il contient une fonction qui permet de récupérer l'ensemble des informations de la joke et de  les mettre sous la forme souhaitée à partir de l'url du site.

## 4. Call API to get one Joke
Désormais, il est nécessaire de créer une instance de jokeService dans l'activité principale afin de récupérer une nouvelle joke depuis l'API.
  
Pour ce faire, nous utilisons une fonction utilisant "subscribeBy" et en paramétrant "onError" et "onSuccess".

Pour éviter de rencontrer l'erreur "NetworkOnMainThreadException", il faut préciser sur quel thread doit s'appliquer la méthode (mainThread).

Une fois que tout est bien configuré et qu'il n'y a plus de messages d'erreurs, on peut afficher notre joke chargée depuis l'API dans le Logcat.

## 5. Leaks killer
On ajoute maintenant un "compositeDisposable" à l'activité principale.
  
Cette instance va stocker la joke une fois qu'elle aura été affichée.
  
Il ne faut pas oublier de vider ce "compositeDisposable" une fois que le moment est venu. On rajoute alors la méthode "OnDestroy()" qui suit la méthode "OnCreate(.)".
On vide alors l'instance de "compositeDisposable" lors de l'appel de cette méthode.
  
## 6. Conclusion
Nous avons donc une application qui peut récupérer une liste de jokes depuis l'API de Chuck Norris et l'afficher dans le Logcat.
  
---------------------------------------------------------------------------------------------------------------------------------------

# III - Display jokes on screen


## 1. Display a single Joke
Notre objet contenant la liste des jokes n'est désormais plus utile puisque l'on va récupérer les jokes depuis l'API.
  
Comme précédemment, l'erreur "CalledFromWrongThreadException" peut apparaître si l'on ne précise pas quel thread doit être utilisé.
Il faut utiliser la méthode "observeOn(.)" afin de le préciser.
  
Puis, on rajoute un bouton à l'interface qui sert à ajouter une nouvelle joke à chaque clic sur celui-ci.
  
## 2. Add a loader
Afin de rendre explicite le fait qu'une joke est en cours de chargement depuis l'API, on rajoute une barre de progression.
Elle est personnalisée et est visible uniquement lors du temps de chargement de la joke. 
De plus, étant donné que le chargement d'une joke est court, on rajoute un délai afin de la voir durant un temps plus concret.
  
## 3. Make the call for multiple jokes with Observable
Il est temps d'effectuer un changement majeur: l'affichage d'un groupe de 10 jokes au lieu d'une seule. 
Pour cela, on change le "Single" qui était utilisé en "Observable".
On utilise ensuite la méthode "repeat(.)" qui permet de répéter la même opération autant de fois que nécessaire.
La méthode "onSuccess" deviens alors "onNext" et "onComplete".
  
## 4. Reload new jokes
Maintenant que tout fonctionne bien, on peut retirer notre bouton de chargement des jokes.

À la place, on ajoute une fonction de rappel, passée en argument à notre adapteur, pour effectuer les consignes souhaitées.
Cela va permettre de:
- ne rien faire si aucune fonction n'est précisée
- appeler la fonction mise en paramètre dans le constructeur de "JokeAdapter"

Il faut ensuite penser à mettre à jour l'instanciation de notre adaptateur puisque l'on a rajouté un paramètre à son constructeur.

Enfin, il est nécessaire de déterminer si l'on a atteint le bas de la page afin de recharger une nouvelle série de jokes sur notre interface.
La fonction de rappel est donc appelé au moment opportun pour afficher de nouvelles jokes.
  
## 5. Conclusion
Nous avons donc à ce stade une application qui peut récupérer une liste de jokes depuis l'API de Chuck Norris sur internet.
Une fois qu'on atteint le bas de la liste, une nouvelle liste vient se charger en dessous de la précédente.
Cela nous permet ainsi d'obtenir une liste infini de jokes.
  
---------------------------------------------------------------------------------------------------------------------------------------

# IV - Make UI great again


## 1. Manage screen rotation

  
## 2. Custom Joke View

  
## 3. Share jokes

  
## 4. Save jokes

  
## 5. Pull to refresh

  
## 6. Conclusion
