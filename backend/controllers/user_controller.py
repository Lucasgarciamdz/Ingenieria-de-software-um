from backend.controllers.base_controller_impl import BaseControllerImpl
from backend.schemas.user_schema import UserSchema
from backend.services.user_service import UserService


class UserController(BaseControllerImpl):
    def __init__(self):
        super().__init__(UserSchema, UserService())
