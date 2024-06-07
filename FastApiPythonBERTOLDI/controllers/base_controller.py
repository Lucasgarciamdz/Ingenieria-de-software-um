"""
Module for the abstract base controller class.
"""

# Importo ABC para definir metodos abstractos
from abc import ABC, abstractmethod
from typing import Type, List

# Vamos a resolver la impedancia entre la base de datos y el modelo de datos
from schemas.base_schema import BaseSchema
from services.base_service import BaseService


class BaseController(ABC):
    """
    Abstract base controller class.
    """

    # Nos permite acceder directamente a los metodos de la clase como si fueran propieades
    @property
    # Define que el metodo sea abstracto
    @abstractmethod
    # La devolucion del metodo es de tipo BaseService
    def service(self) -> BaseService:
        """
        Service to access database
        """

    @property
    @abstractmethod
    # Este metodo nos sirve para mapear el JSON request a un schema
    def schema(self) -> Type[BaseSchema]:
        """
        Pydantic Schema to validate data
        """

    @abstractmethod
    def get_all(self) -> List[BaseSchema]:
        """
        Get all data
        """

    @abstractmethod
    def get_one(self, id_key: int) -> BaseSchema:
        """
        Get one data
        """

    @abstractmethod
    def save(self, schema: BaseSchema) -> BaseSchema:
        """
        Save data
        """

    @abstractmethod
    def update(self, id_key: int, schema: BaseSchema) -> BaseSchema:
        """
        Update data
        """

    @abstractmethod
    def delete(self, id_key: int) -> None:
        """
        Delete data
        """
