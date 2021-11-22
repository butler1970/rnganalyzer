package com.tiwalasautak.rng.table

import com.jakewharton.picnic.*
import com.jakewharton.picnic.Table

class Table {
    private val body = TableSection.Builder()

    fun addRow(row: Row) {
        body.addRow(row)
    }

    fun addRow(gridACells: Array<String>, gridBCells: Array<String>) {
        val rowBuilder = Row.Builder()

        gridACells.forEach { cell ->
            rowBuilder.addCell(cell)
        }

        rowBuilder.addCell(Cell("") {
            style = CellStyle {
                borderTop = false
                borderBottom = false
            }
        })

        gridBCells.forEach { cell ->
            rowBuilder.addCell(cell)
        }

        body.addRow(rowBuilder.build())
    }

    fun buildTable(): String {
        val table = Table.Builder()
            .setCellStyle(
                CellStyle.Builder()
                    .setBorder(true)
                    .setPaddingLeft(0)
                    .setPaddingRight(0)
                    .build()
            )
            .setTableStyle(TableStyle { borderStyle = BorderStyle.Solid })
            .setBody(body.build())

        return table.build().toString()
    }

}