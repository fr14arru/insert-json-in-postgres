# Storing JSON

Pet project para analizar cómo almacenar JSON puede impactar en base de datos y en los tiempos de ejecución
de la aplicación.

Se prueba a insertar el JSON en una base de datos Postgres como:
* Texto plano
* JSON
* JSONB
* Array de bytes
* Array de bytes comprimido con GZIP
* Array de bytes comprimido con ZSTD
* Array de bytes comprimido con Snappy

En todos los tests se ejecutan la inserción de 100000 registros de datos autogenerados.

## Algunos tiempos de ejemplo de la ejecución de una batería de tests:

### Texto plano
| Métrica                              | Tiempo (milisegundos) |
|--------------------------------------|-----------------------|
| Tiempo de construcción de entidades  | 2182                  |
| Tiempo de inserción en base de datos | 49394                 |
| Tamaño de tabla de base de datos     | 395 MB                |

### JSON
| Métrica                              | Tiempo (milisegundos) |
|--------------------------------------|-----------------------|
| Tiempo de construcción de entidades  | 255                   |
| Tiempo de inserción en base de datos | 78427                 |
| Tamaño de tabla de base de datos     | 394 MB                |

### JSONB
| Métrica                              | Tiempo (milisegundos) |
|--------------------------------------|-----------------------|
| Tiempo de construcción de entidades  | 39                    |
| Tiempo de inserción en base de datos | 85416                 |
| Tamaño de tabla de base de datos     | 434 MB                |

### Array de bytes
| Métrica                              | Tiempo (milisegundos) |
|--------------------------------------|-----------------------|
| Tiempo de construcción de entidades  | 8513                  |
| Tiempo de inserción en base de datos | 62460                 |
| Tamaño de tabla de base de datos     | 480 MB                |

### Array de bytes comprimido con GZIP
| Métrica                              | Tiempo (milisegundos) |
|--------------------------------------|-----------------------|
| Tiempo de construcción de entidades  | 12378                 |
| Tiempo de inserción en base de datos | 29576                 |
| Tiempo de ejecución de consulta      | 2693                  |
| Tiempo de mapeo a objeto de Java     | 4589                  |
| Tamaño de tabla de base de datos     | 277 MB                |

### Array de bytes comprimido con ZSTD
| Métrica                              | Tiempo (milisegundos) |
|--------------------------------------|-----------------------|
| Tiempo de construcción de entidades  | 11905                 |
| Tiempo de inserción en base de datos | 31430                 |
| Tiempo de ejecución de consulta      | 3044                  |
| Tiempo de mapeo a objeto de Java     | 17771                 |
| Tamaño de tabla de base de datos     | 282 MB                |


### Array de bytes comprimido con Snappy
| Métrica                              | Tiempo (milisegundos) |
|--------------------------------------|-----------------------|
| Tiempo de construcción de entidades  | 3523                  |
| Tiempo de inserción en base de datos | 41056                 |
| Tiempo de ejecución de consulta      | 6990                  |
| Tiempo de mapeo a objeto de Java     | 4306                  |
| Tamaño de tabla de base de datos     | 415 MB                |