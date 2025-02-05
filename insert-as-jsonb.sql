DO $$
   DECLARE

    original_jsonb jsonb := '{
    "product": {
        "productId": "81933",
        "productType": {
            "productTypeId": "VE",
            "productTypePublicId": "VE",
            "productTypeNames": [
                {
                    "localeLanguageCode": "es-ES",
                    "name": "Tornillo de venta",
                    "publicName": "Tornillo de venta"
                }
            ]
        },
        "salesUnitOfMeasureId": "UN",
        "brand": {
            "brandId": "5341",
            "name": "ALBEMAR"
        },
        "scientificName": "HELIX ASPERSA",
        "usageFormatQuantity": 0.0,
        "usageFormatQuantityUnitOfMeasureId": null,
        "creationDate": "2023-05-08",
        "contentNetAmount": 0.0,
        "contentGrossAmount": 0.0,
        "contentLegalAmount": 0.0,
        "contentLegalUnitOfMeasureId": null,
        "contentEdibleLiquid": "X",
        "naturalWeightLossPercentage": 0.0,
        "wastePercentage": 0.0,
        "paymentDeadlineId": "P",
        "supplyTypeId": "4",
        "isAssistedSales": false,
        "isBulk": false,
        "isVariableWeight": false,
        "isClearable": true,
        "isDangerousGood": false,
        "isDonatable": true,
        "lifetimeTotal": 4,
        "lifetimeLogistics": null,
        "lifetimeStore": 0,
        "lifetimeGuaranteed": null,
        "lifetimeUntilWithdrawal": null,
        "lifetimeUnitOfMeasureId": "D",
        "orderType": {
            "orderTypeId": "1",
            "orderTypeNames": [
                {
                    "localeLanguageCode": "es-ES",
                    "description": "Frescos"
                }
            ]
        },
        "isWrapper": null,
        "storeMovementUnitOfMeasureId": "UN",
        "taricCode": null,
        "freightTemperatureMaximum": 0.0,
        "freightTemperatureMinimum": 0.0,
        "freightTemperatureUnitOfMeasureId": null,
        "hasStockControl": null,
        "productHierarchies": [
            {
                "hierarchyTypeId": "2",
                "hierarchyLevel": {
                    "hierarchyLevelId": "06710302"
                },
                "names": [
                    {
                        "localeLanguageCode": "es-ES",
                        "name": "ESTRUCTURA DE SECCIONES"
                    }
                ]
            },
            {
                "hierarchyTypeId": "1",
                "hierarchyLevel": {
                    "hierarchyLevelId": "18101120303"
                },
                "names": [
                    {
                        "localeLanguageCode": "es-ES",
                        "name": "JERARQUIA DE PRESCRIPCIÓN"
                    }
                ]
            },
            {
                "hierarchyTypeId": "10",
                "hierarchyLevel": {
                    "hierarchyLevelId": "4379"
                },
                "names": [
                    {
                        "localeLanguageCode": "es-ES",
                        "name": "HÁBITOS DE CONSUMO PESCADO"
                    }
                ]
            },
            {
                "hierarchyTypeId": "3",
                "hierarchyLevel": {
                    "hierarchyLevelId": "10"
                },
                "names": [
                    {
                        "localeLanguageCode": "es-ES",
                        "name": "BUSINES-HIERARCHY"
                    }
                ]
            }
        ],
        "productTaxRates": [
            {
                "countryIso2AlfaCode": "ES",
                "countryId": null,
                "countryAlternativeCode": null,
                "countryPublicId": null,
                "taxId": "TTX1",
                "taxRateId": "2",
                "publicId": "3",
                "deductiblePercentage": 100.0,
                "taxIdPublicId": "1"
            },
            {
                "countryIso2AlfaCode": "ES",
                "countryId": null,
                "countryAlternativeCode": null,
                "countryPublicId": null,
                "taxId": "ZIP2",
                "taxRateId": "7",
                "publicId": "30",
                "deductiblePercentage": 0.0,
                "taxIdPublicId": "4"
            },
            {
                "countryIso2AlfaCode": "ES",
                "countryId": null,
                "countryAlternativeCode": null,
                "countryPublicId": null,
                "taxId": "ZIP1",
                "taxRateId": "A",
                "publicId": "9",
                "deductiblePercentage": 0.0,
                "taxIdPublicId": "3"
            }
        ],
        "productSpecialTaxRates": [],
        "productNames": [
            {
                "localeLanguageCode": "es-ES",
                "name": "CARACOL VIVO ALBEMAR 500 g"
            }
        ],
        "hazardousMaterials": [],
        "countriesFiscalTypes": [
            {
                "countryIso2AlfaCode": "ES",
                "countryFiscalTypeId": "1",
                "countryFiscalTypeNames": [
                    {
                        "localeLanguageCode": "es-ES",
                        "name": "BIEN CORRIENTE"
                    }
                ]
            },
            {
                "countryIso2AlfaCode": "PT",
                "countryFiscalTypeId": "2",
                "countryFiscalTypeNames": [
                    {
                        "localeLanguageCode": "es-ES",
                        "name": "BIEN EXISTENCIAS"
                    }
                ]
            }
        ]
    },
    "handlingUnitId": "UN",
    "productFormatPublicId": "81933",
    "creationDate": "2023-05-08",
    "firstActivationDate": "2023-06-24",
    "salesFormatAmount": 500.0,
    "salesFormatUnitOfMeasureId": "G",
    "isVariationLabelPrintingActive": true,
    "isOrderable": null,
    "isSellable": null,
    "dimensionsLength": 19.0,
    "dimensionsWidth": 11.0,
    "dimensionsHeight": 10.0,
    "dimensionsUnitOfMeasureId": "CM",
    "weightsNet": 500.0,
    "weightsGross": 500.0,
    "weightsDrained": 10.0,
    "weightsPieceAverageNet": 0.0,
    "weightsUnitOfMeasureId": "G",
    "tareQuantity": 0.0,
    "tareUnitOfMeasureId": "G",
    "lowerFormatHandlingUnitId": "UN",
    "lowerFormatPublicId": "81933",
    "lowerFormatConversionRatio": 1.0,
    "scaleAuxiliaryMaterialProductId": null,
    "scaleAuxiliaryMaterialHandlingUnitId": null,
    "scaleAuxiliaryMaterialPublicId": null,
    "isTransformableInScaleDueToNaturalWeightLoss": false,
    "transformableDueToNaturalWeightLossMinimumQuantity": null,
    "transformableDueToNaturalWeightLossMaximumQuantity": null,
    "transformableDueToNaturalWeightLossUnitOfMeasureId": null,
    "productFormatNames": [
        {
            "localeLanguageCode": "es-ES",
            "descriptionType": {
                "descriptionTypeId": "COM",
                "descriptionTypeNames": [
                    {
                        "localeLanguageCode": "es-ES",
                        "name": "DESCRIPCIÓN COMERCIAL"
                    }
                ]
            },
            "name": "CARACOL VIVO"
        },
        {
            "localeLanguageCode": "es-ES",
            "descriptionType": {
                "descriptionTypeId": "TIC",
                "descriptionTypeNames": [
                    {
                        "localeLanguageCode": "es-ES",
                        "name": "DESCRIPCIÓN TICKET"
                    }
                ]
            },
            "name": "CARACOL VIVO"
        },
        {
            "localeLanguageCode": "es-ES",
            "descriptionType": {
                "descriptionTypeId": "EXT",
                "descriptionTypeNames": [
                    {
                        "localeLanguageCode": "es-ES",
                        "name": "DESCRIPCIÓN EXTENDIDA"
                    }
                ]
            },
            "name": "CARACOL VIVO"
        }
    ],
    "gtinProductFormats": [
        {
            "gtinCode": "8437022077017",
            "isMain": true
        }
    ],
    "productFormatCommercialStatuses": [
        {
            "commercialStatus": {
                "commercialStatusId": "A",
                "commercialStatusNames": [
                    {
                        "localeLanguageCode": "es-ES",
                        "name": "ESTADO A"
                    }
                ]
            },
            "startDate": "2023-06-24",
            "endDate": "9999-12-31"
        }
    ],
    "labelTypes": [
        {
            "id": "20",
            "isDefault": true,
            "startDate": "2023-06-19",
            "endDate": "2999-12-31",
            "labelTypeNames": [
                {
                    "localeLanguageCode": "es-ES",
                    "name": " BALDA"
                }
            ],
            "salesLabelTypeScopeId": "1",
            "salesLabelTypeScopeName": "Unitaria"
        },
        {
            "id": "21",
            "isDefault": false,
            "startDate": "2022-01-01",
            "endDate": "2023-06-18",
            "labelTypeNames": [
                {
                    "localeLanguageCode": "es-ES",
                    "name": "PALET"
                }
            ],
            "salesLabelTypeScopeId": "1",
            "salesLabelTypeScopeName": "Unitaria"

        }
    ],
    "image": {
        "digitalAssetId": "8618c8b7-3f8e-4d82-b098-008bfcb67505",
        "productId": 81933,
        "handlingUnitId": "UN",
        "productFormatPublicId": 81933,
        "imageUrl": "https://mercaimages-res.cloudinary.com/image/upload/t_ImgProMP/8618c8b7-3f8e-4d82-b098-008bfcb67505",
        "isMain": true,
        "archiveTypeId": "TA00000001",
        "archiveTypeDescription": "A – Fotografía de producto con un solo GTIN",
        "sideTypeId": "CP00000002",
        "sideTypeDescription": "1 - Frontal",
        "angleTypeId": "IA00000004",
        "angleTypeDescription": "N - Sin ángulo",
        "envelopeTypeId": "EN00000001",
        "envelopeTypeDescription": "1 - Dentro del envoltorio",
        "imageSizeId": "MP",
        "imageSizeDescription": "Medium size PNG format",
        "scopeTypeId": null,
        "scopeTypeDescription": null,
        "fromDateTime": "2023-06-19T17:31:09+02:00",
        "toDateTime": "2999-12-31T00:00:00+01:00",
        "lastUpdatedDateTime": "2023-06-19T17:31:09+02:00",
        "variantDescription": null
            }
        }';
       all_jsons jsonb[] := '{}';

    BEGIN
     FOR i IN 1..10000 LOOP
         all_jsons := array_append(all_jsons, original_jsonb);
         END loop;

    INSERT INTO json_as_jsonb(json) select unnest(all_jsons);

END    $$;