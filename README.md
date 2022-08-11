<h1 align="center">
    R E C I P E S
</h1>

___

## 🚀 **Description**

Sometimes, it's useful to have a tool that can help you share a piece of code with other programmers. Actually, there is a website called Pastebin that does exactly that. A huge downside of Pastebin is that every piece of code you share automatically becomes available for the public. This could present a problem since many programmers work under the NDA (Non-disclosure agreement) which prohibits the use of such services to prevent the source code from leaking.

If there is a team of programmers who work in the same company and want to exchange pieces of code, they can solve this problem by using their own implementation of Pastebin. Such a web service is supposed to be accessible only locally, not via the Internet.

###### *For detailed information, refer to the [**JetBrains Academy**](https://hyperskill.org/projects/130?track=12).*

___

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
