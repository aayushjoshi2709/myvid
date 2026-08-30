CREATE TABLE IF NOT EXISTS service (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    "updatedAt" TIMESTAMP,
    "createdAt" TIMESTAMP,
    status VARCHAR(10) NOT NULL DEFAULT 'ACTIVE',
    "serviceName" VARCHAR(30) NOT NULL,
    "serviceUrl" VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS endpoint (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    "updatedAt" TIMESTAMP,
    "createdAt" TIMESTAMP,
    status VARCHAR(10) NOT NULL DEFAULT 'ACTIVE',
    "serviceId" UUID NOT NULL,
    endpoint VARCHAR(150) NOT NULL,
    method VARCHAR(10) NOT NULL,
    roles JSONB
);
