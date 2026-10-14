package com.recuperacion.sistemapedidos.dto;

public class PedidoRequestDTO {

    private Long usuarioId;
    private String producto;
    private Integer cantidad;

    public PedidoRequestDTO() {
    }

    public PedidoRequestDTO(Long usuarioId, String producto, Integer cantidad) {
        this.usuarioId = usuarioId;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}