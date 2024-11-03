


to start mongoDB in local using brew - ```brew services start mongodb-community@8.0```
to stop mongoDB in local using brew - ```brew services stop mongodb-community@8.0```

<h2></h2>

1. `@indexed(unique=true)` - for indexing along with this there is entry needed in properties files for indexing.
   1. properties needs to be added - ```spring.data.mongodb.auto-index-creation=true```
2. `@DBRef` - for referencing one entity with another entity in MongoDB. It will store reference/objectId if of the reference id.
3. 