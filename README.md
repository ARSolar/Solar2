# SolarGen

Aplicativo Android simples para estimar a geração anual de energia de um sistema fotovoltaico.

Este projeto é um exemplo básico em Kotlin que utiliza ViewBinding e fornece uma interface em português para entrada dos dados principais:

- Quantidade de placas em determinada orientação/inclinação
- Ângulo de orientação (azimute) em graus
- Inclinação das placas em graus
- Horários de sombra

A estimativa é calculada de forma simplificada, utilizando valores médios de irradiação solar no Brasil.

Para compilar o projeto, é necessário o Android SDK e o Gradle adequados. Execute:

```bash
./gradlew build
```

Caso não possua ambiente configurado, utilize o Android Studio.
