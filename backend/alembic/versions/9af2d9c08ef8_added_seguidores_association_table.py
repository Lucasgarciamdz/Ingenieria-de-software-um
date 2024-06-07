"""Added seguidores association table

Revision ID: 9af2d9c08ef8
Revises: 
Create Date: 2024-06-07 14:31:57.854774

"""
from typing import Sequence, Union

from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision: str = '9af2d9c08ef8'
down_revision: Union[str, None] = None
branch_labels: Union[str, Sequence[str], None] = None
depends_on: Union[str, Sequence[str], None] = None


def upgrade() -> None:
    # ### commands auto generated by Alembic - please adjust! ###
    op.create_table('users',
        sa.Column('id', sa.Integer(), primary_key=True, autoincrement=True),
        sa.Column('nombre', sa.String(), nullable=True),
        sa.Column('apellido', sa.String(), nullable=True),
        sa.Column('contrasena', sa.String(), nullable=True)
    )
    op.create_table('seguidores',
        sa.Column('seguidor_id', sa.Integer(), sa.ForeignKey('users.id'), nullable=False),
        sa.Column('seguido_id', sa.Integer(), sa.ForeignKey('users.id'), nullable=False)
    )
    # ### end Alembic commands ###


def downgrade() -> None:
    # ### commands auto generated by Alembic - please adjust! ###
    op.drop_table('seguidores')
    op.drop_table('users')
    # ### end Alembic commands ###