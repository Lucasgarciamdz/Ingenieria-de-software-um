from backend.models.user_model import UserModel
from backend.repositories.user_repository import UserRepository
from backend.schemas.user_schema import UserSchema
from backend.services.base_service_impl import BaseServiceImpl


class UserService(BaseServiceImpl):

    def __init__(self):
        super().__init__(repository=UserRepository(), model=UserModel, schema=UserSchema)
