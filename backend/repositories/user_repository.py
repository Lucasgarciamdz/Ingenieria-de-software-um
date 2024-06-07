from backend.models.user_model import UserModel
from backend.repositories.base_repository_impl import BaseRepositoryImpl
from backend.schemas.user_schema import UserSchema


class UserRepository(BaseRepositoryImpl):
    def __init__(self):
        super().__init__(UserModel, UserSchema)
