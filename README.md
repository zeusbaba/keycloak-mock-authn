# Mock User Provider for Keycloak

This project contains `mock-user-provider` implementation to enable `authentication` via any norwegian personnummer  

## Usage

### Build & Deploy

```sh
mvn clean install
```

Copy jar to keycloak `providers` directory

```sh
cp target/keycloak-mock-authn-1.0.1.jar $KEYCLOAK_HOME/providers
```

### Configuring User Provider

See [Configuring User Provider for Mock Authentication](docs/mock-user-provider.md)

## Disclaimer  
this project is an adaptation from [Niko Kobler's work](https://github.com/dasniko)  
Reused from open source, giving back to open source!  
