-- ������� ������� tableware, ���� ��� �� ����������
CREATE TABLE IF NOT EXISTS tableware (
    id SERIAL PRIMARY KEY,              -- ���������� �������������
    name VARCHAR(255) NOT NULL UNIQUE,  -- �������� ������ (���������� � �� ����� ���� ������)
    material VARCHAR(100) NOT NULL UNIQUE, -- �������� (���������� � �� ����� ���� ������)
    type VARCHAR(50) NOT NULL,          -- ��� ������ (�� ����� ���� ������)
    volume_ml INTEGER NOT NULL,         -- ����� � ����������� (�� ����� ���� ������)
    price_rub INTEGER NOT NULL          -- ���� � ������ (�� ����� ���� ������)
);

-- �������� ������ � �������
INSERT INTO tableware (name, material, type, volume_ml, price_rub)
VALUES
    ('�����', '��������', '�������', 500, 300),
    ('����������', '������', '������', 300, 150),
    ('�����', '�����', '���������', 2000, 1200),
    ('��������', '��������', '��������', 5000, 800),
    ('��������', '������', '������', 400, 250),
    ('������', '����������� �����', '������', 1500, 750),
    ('�����', '������', '�����', 40, 100),
    ('���������', '������', '�������', 1000, 350),
    ('����������', '����������� �����', '�����', 40, 60),
    ('�������', '����������� �����', '�����', 0, 50)
ON CONFLICT (name, material) DO NOTHING; -- �������� ������������ �������