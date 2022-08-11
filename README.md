<h1 align="center">
    R E C I P E S
</h1>

___

## 🚀 **Description**

Sometimes it is very difficult to resist the desire to cook something special. But what if you lost the recipe? Or is your beloved grandmother too busy to answer the phone and remind you of the recipe for your favorite cake?

Let's solve this problem with `Recipes` - a program that can store all recipes in one place. The program is a multi-user web service based on Spring Boot, which allows you to store, extract, update and delete recipes. In fact, this is a REST API application that uses a lightweight H2 database as a recipe repository.

###### *For detailed information, refer to the [**JetBrains Academy**](https://hyperskill.org/projects/180?track=12).*

## 🔬 **Examples**

### **Example 1:** 

`POST /api/recipe/new` request without authentication

```json
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```

Status code: `401 (Unauthorized)`

<br>

### **Example 2:** 

`POST /api/register` request without authentication

```json
{
   "email": "someName@gmail.com",
   "password": "12345"
}
```

Status code: `200 (Ok)`

Further `POST /api/recipe/new` request with basic authentication; email (login): someName@gmail.com, and password: 12345

```json
{
   "name": "Mint Tea",
   "category": "beverage",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```

Response:

```json
{
   "id": 1
}
```

Further `PUT /api/recipe/1` request with basic authentication; email (login): someName@gmail.com, and password: 12345

```json
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```

Status code: `204 (No Content)`

Further `GET /api/recipe/1` request with basic authentication; email (login): someName@gmail.com, and password: 12345

Response:

```json
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "date": "2020-01-02T12:11:25.034734",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```

<br>

### **Example 3:** 

`DELETE /api/recipe/1` request request with basic authentication; email (login): someName@gmail.com, and password: 12345

Status code: `204 (No Content)`

`DELETE /api/recipe/1` request request with basic authentication; email (login): someName@gmail.com, and password: 12345

Status code: `404 (Not found)`

<br>

### **Example 4:** 

`GET /api/recipe/1` request request with basic authentication; email (login): someName@gmail.com, and password: 12345

Status code: `404 (Not found)`
