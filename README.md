# recipe
[![CircleCI](https://circleci.com/gh/TomLouisKeller/recipe.svg?style=svg)](https://circleci.com/gh/TomLouisKeller/recipe) 
[![codecov](https://codecov.io/gh/TomLouisKeller/recipe/branch/master/graph/badge.svg)](https://codecov.io/gh/TomLouisKeller/recipe)


Demo Project with Spring Boot 2.0


#### TODO
 - Set 'spring.profiles.active:' as environment variable or in /resources/application.yml 
possible values are: default, development, production (default with development)
 - Either: use 'de.flapdoodle.embed.mongo' as 'compile' dependency or copy 
 /resources/config/sample/application-default.sample.yml to /resources/config/application-{activeProfile}.yml
 and set spring.data.mongodb.uri to the correct uri