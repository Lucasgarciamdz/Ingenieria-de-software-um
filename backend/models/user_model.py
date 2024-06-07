from sqlalchemy import Table, Column, Integer, String, ForeignKey
from sqlalchemy.orm import relationship
from backend.models.base_model import BaseModel

seguidores_association = Table('seguidores', BaseModel.metadata,
    Column('seguidor_id', Integer, ForeignKey('users.id')),
    Column('seguido_id', Integer, ForeignKey('users.id'))
)


class UserModel(BaseModel):
    __tablename__ = "users"

    nombre = Column(String)
    apellido = Column(String)
    contrasena = Column(String)

    seguidosList = relationship('UserModel', secondary=seguidores_association, primaryjoin=id==seguidores_association.c.seguidor_id, backref='seguidoresList')