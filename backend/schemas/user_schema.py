from typing import Optional, List, ForwardRef
from backend.schemas.base_schema import BaseSchema

UserSchema = ForwardRef('UserSchema')


class UserSchema(BaseSchema):
    id: int
    nombre: str
    apellido: str
    contrasena: str
    seguidosList: Optional[List[UserSchema]]
    seguidoresList: Optional[List[UserSchema]]


UserSchema.update_forward_refs()
